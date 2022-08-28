import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-enterprise-dialog',
  templateUrl: './enterprise-dialog.component.html',
  styleUrls: ['./enterprise-dialog.component.css'],
})
export class EnterpriseDialogComponent implements OnInit {
  constructor(
    private router: Router,
    private dialog: MatDialogRef<EnterpriseDialogComponent>
  ) {}

  ngOnInit(): void { /* TODO document why this method 'ngOnInit' is empty */ }

  closeDialog() {
    this.dialog.close();
    this.router.navigate(["/users"])
  }


}
