import { Component, OnInit } from '@angular/core';
import { ProfileDialogComponent } from '../profile-dialog/profile-dialog.component';
import { MatDialog } from '@angular/material/dialog';

@Component({
  selector: 'app-open-profile',
  templateUrl: './open-profile.component.html',
  styleUrls: ['./open-profile.component.css'],
})
export class OpenProfileComponent implements OnInit {
  constructor(
    private profileDialogService: MatDialog
  ) {}

  ngOnInit(): void {
    this.profileDialogService.open(ProfileDialogComponent, { disableClose: true });
  }
}
