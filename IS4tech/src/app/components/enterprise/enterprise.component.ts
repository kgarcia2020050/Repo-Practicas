import { Component, OnInit } from '@angular/core';
import { Enterprise } from 'src/app/models/enterprise';
import { EnterpriseService } from 'src/app/services/enterprise.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-enterprise',
  templateUrl: './enterprise.component.html',
  styleUrls: ['./enterprise.component.css'],
})
export class EnterpriseComponent implements OnInit {
  public enterprises: Enterprise;

  public asc: boolean = true;
  public isFirst: boolean;
  public isLast: boolean;
  public page: number = 0;
  public totalPages: number;
  public search: any;
  public postEnterprise: Enterprise;
  public addEnterprise: boolean = true;
  public getEnterprise: Enterprise;

  public changeForm: boolean = false;
  public dataServiceEnterprise: Enterprise;
  public itemSelected: number;

  constructor(private enterpriseService: EnterpriseService) {
    this.getEnterprise = new Enterprise(0, '', 1, 0);

    this.postEnterprise = new Enterprise(0, '', 1, 0);
  }

  ngOnInit(): void {
    this.getEnterprises();
  }

  openDialog() {
    this.addEnterprise = true;
    this.itemSelected = 0;
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
        this.addEnterprise = true;
        Swal.fire({
          icon: 'success',
          text: 'Perfil modificado exitosamente.',
        });
        this.getEnterprises();
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
    source.status = source.status ? 1 : 0;
    source.permission = source.permission ? 1 : 0;
    return (
      source.name === target.name &&
      source.status === target.status &&
      source.permission === target.permission
    );
  }

  validChangeForm() {
    if (!this.addEnterprise) {
      this.changeForm = this.equals(
        this.getEnterprise,
        this.dataServiceEnterprise
      );
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
        this.getEnterprises();

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
