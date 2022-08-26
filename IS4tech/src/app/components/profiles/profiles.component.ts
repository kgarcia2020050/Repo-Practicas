import { Component, OnInit } from '@angular/core';
import { Profile } from 'src/app/models/profile';
import { ProfileService } from 'src/app/services/profile.service';
import { MatDialog } from '@angular/material/dialog';
import { ProfileDialogComponent } from '../profile-dialog/profile-dialog.component';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-profiles',
  templateUrl: './profiles.component.html',
  styleUrls: ['./profiles.component.css'],
  providers: [ProfileService],
})
export class ProfilesComponent implements OnInit {
  public profiles: Profile;
  public getProfile: Profile;
  public asc: boolean = true;
  public isFirst: boolean;
  public isLast: boolean;
  public page: number = 0;
  public search: any;

  constructor(
    private profileService: ProfileService,
    public dialog: MatDialog
  ) {
    this.getProfile = new Profile(0, '', 1);
  }

  ngOnInit(): void {
    this.getProfiles();
  }

  openDialog() {
    this.dialog.open(ProfileDialogComponent);
  }

  findById(id) {
    this.profileService.getProfile(id).subscribe({
      next: (response: any) => {
        this.getProfile = response;
      },
    });
  }

  putProfile(id) {
    this.profileService.putProfile(this.getProfile, id).subscribe({
      next: () => {
        Swal.fire({
          icon: 'success',
          text: 'Perfil modificado exitosamente.',
        }).then(() => {
          this.getProfiles();
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

  getProfiles() {
    this.profileService.getProfiles(this.page, 6, 'name', this.asc).subscribe({
      next: (response: any) => {
        this.profiles = response.content;
        this.isFirst = response.first;
        this.isLast = response.last;
      },
    });
  }

  goBack() {
    if (!this.isFirst) {
      this.page--;
      this.getProfiles();
    }
  }

  goAhead() {
    if (!this.isLast) {
      this.page++;
      this.getProfiles();
    }
  }

  filter() {
    if (this.asc) {
      this.asc = false;
    } else {
      this.asc = true;
    }
    this.getProfiles();
  }
}
