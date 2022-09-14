import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Enterprise } from 'src/app/models/enterprise';
import { EnterpriseService } from 'src/app/services/enterprise.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-enterprise',
  templateUrl: './enterprise.component.html',
  styleUrls: ['./enterprise.component.css'],
})
export class EnterpriseComponent implements OnInit {
  public getEnterprise: Enterprise;
  public asc: boolean = true;
  public isFirst: boolean;
  public isLast: boolean;
  public page: number = 0;
  public totalPages: number;
  public enterprises: Enterprise;
  public search: any;
  public postEnterprise: Enterprise;
  public addEnterprise: boolean = true;
  public itemSelected: number;
  public dataServiceEnterprise: Enterprise;
  public changeForm: boolean = false;


  constructor(
    private enterpriseService: EnterpriseService,
    private router: Router
  ) {
    this.postEnterprise = new Enterprise(0, '', 1);
    this.getEnterprise = new Enterprise(0, '', 1);

  }

  ngOnInit(): void {
    this.getEnterprises();
   
  }

  openDialog() {
    this.addEnterprise = true;
    this.dataServiceEnterprise = null;
    this.getEnterprise = null;
    this.changeForm = false;
  }

  getEnterprises() {
    this.enterpriseService
      .getEnterprisesPagination(this.page, 6, 'name', this.asc)
      .subscribe({
        next: (response: any) => {
          this.enterprises = response.content;
          this.isFirst = response.first;
          this.isLast = response.last;
          this.totalPages = response.totalPages;
        },
      });
  }
  findById(id) {
    this.enterpriseService.getEnterprise(id).subscribe({
      next: (response: any) => {
        this.addEnterprise = false;
        this.itemSelected = id;
        this.dataServiceEnterprise = Object.assign({}, response);
        this.getEnterprise = Object.assign({}, response);

        this.validChangeForm();
      },
    });
  }

  putEnterprise(id) {
    this.enterpriseService.putEnterprise(this.getEnterprise, id).subscribe({
      next: () => {
        Swal.fire({
          icon: 'success',
          text: 'Perfil modificado exitosamente.',
        }).then(() => {
          this.getEnterprises();
        });
      },
      error: (error: any) => {
        if (error.error.errors) {
          Swal.fire({
            icon: 'error',
            text: error.error.errors[0].defaultMessage,
          });
        } else {
          Swal.fire({
            icon: 'error',
            text: error.error.message,
          });
        }
      },
    });
  }

  equals(source: Enterprise, target: Enterprise): boolean {
    return source.name === target.name && source.status === target.status;
  }


  validChangeForm() {
    if (!this.addEnterprise) {
      this.changeForm = this.equals(this.getEnterprise, this.dataServiceEnterprise);
    }
  }

  goBack() {
    if (!this.isFirst) {
      this.page--;
      this.getEnterprises();
    }
  }

  goAhead() {
    if (!this.isLast) {
      this.page++;
      this.getEnterprises();
    }
  }

  newEnterprise(addForm) {
    this.enterpriseService.postEnterprise(this.postEnterprise).subscribe({
      next: () => {
        addForm.reset();
        Swal.fire({
          text: 'Empresa agregada exitosamente',
          icon: 'success',
        });
      },
      error: (error: any) => {
        if (error.error.errors) {
          Swal.fire({
            icon: 'error',
            text: error.error.errors[0].defaultMessage,
          });
        } else {
          Swal.fire({
            icon: 'error',
            text: error.error.message,
          });
        }
      },
    });
  }
}
