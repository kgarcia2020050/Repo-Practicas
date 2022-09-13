import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Enterprise } from 'src/app/models/enterprise';
import { EnterpriseService } from 'src/app/services/enterprise.service';
import Swal from 'sweetalert2';


@Component({
  selector: 'app-enterprise',
  templateUrl: './enterprise.component.html',
  styleUrls: ['./enterprise.component.css']
})
export class EnterpriseComponent implements OnInit {
  public getEnterprise: Enterprise;
  public asc: boolean = true;
  public isFirst: boolean;
  public isLast: boolean;
  public page: number = 0;
  public enterprises: Enterprise;
  public search: any;
  public postEnterprise: Enterprise;





  constructor(private enterpriseService: EnterpriseService, private router: Router) {
    this.getEnterprise = new Enterprise(0, '',1);

  }

  ngOnInit(): void {
    this.getEnterprises();
  }

  getEnterprises() {
    this.enterpriseService.getEnterprisesPagination(this.page, 6, 'name', this.asc).subscribe({
      next: (response: any) => {
        console.log(response)
        this.enterprises = response.content;
        this.isFirst = response.first;
        this.isLast = response.last;
      },
    });
  }

  newEnterprise(addForm) {
    this.enterpriseService.postEnterprise(this.postEnterprise).subscribe({
      next: () => {
        addForm.reset();
        Swal.fire({
          text: 'Empresa agregada exitosamente',
          icon: 'success',
        })
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
