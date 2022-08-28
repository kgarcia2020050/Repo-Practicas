import { Component, OnInit } from '@angular/core';
import { UserDialogComponent } from '../user-dialog/user-dialog.component';
import { MatDialog } from '@angular/material/dialog';

@Component({
  selector: 'app-open-user',
  templateUrl: './open-user.component.html',
  styleUrls: ['./open-user.component.css'],
})
export class OpenUserComponent implements OnInit {
  constructor(private dialog: MatDialog) {}

  ngOnInit(): void {
    this.dialog.open(UserDialogComponent, { disableClose: true });
  }
}
