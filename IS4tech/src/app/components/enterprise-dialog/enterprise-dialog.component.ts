import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MatDialogRef } from '@angular/material/dialog';
import { EnterpriseService } from 'src/app/services/enterprise.service';
import { Enterprise } from 'src/app/models/enterprise';
import Swal from 'sweetalert2';


@Component({
  selector: 'app-enterprise-dialog',
  templateUrl: './enterprise-dialog.component.html',
  styleUrls: ['./enterprise-dialog.component.css'],
})
export class EnterpriseDialogComponent implements OnInit {
  public postEnterprise: Enterprise;
  public enterprise: Enterprise;


  constructor(
    private router: Router,
    private dialog: MatDialogRef<EnterpriseDialogComponent>,
    private enterpriseService: EnterpriseService
  ) {
    this.postEnterprise = new Enterprise(0, '');

  }

  ngOnInit(): void { /* TODO document why this method 'ngOnInit' is empty */ }

  closeDialog() {
    this.dialog.close();
    this.router.navigate(["/users"])
  }

  newEnterprise(addForm) {
    this.enterpriseService.postEnterprise(this.postEnterprise).subscribe({
      next: () => {
        addForm.reset();
        Swal.fire({
          text: 'Empresa agregada exitosamente',
          icon: 'success',
        }).then(() => {
          this.closeDialog();
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
            text: error.error,
          });
        }
      },
    });
  }


}
