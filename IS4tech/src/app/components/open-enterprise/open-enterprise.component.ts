import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { EnterpriseDialogComponent } from '../enterprise-dialog/enterprise-dialog.component';

@Component({
  selector: 'app-open-enterprise',
  templateUrl: './open-enterprise.component.html',
  styleUrls: ['./open-enterprise.component.css'],
})
export class OpenEnterpriseComponent implements OnInit {
  constructor(private dialog: MatDialog) {}

  ngOnInit(): void {
    this.dialog.open(EnterpriseDialogComponent, { disableClose: true });
  }
}
