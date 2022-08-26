import { Component, OnInit } from '@angular/core';
import Swal from 'sweetalert2';
import { Profile } from 'src/app/models/profile';
import { ProfileService } from 'src/app/services/profile.service';
import { MatDialog } from '@angular/material/dialog';

@Component({
  selector: 'app-profile-dialog',
  templateUrl: './profile-dialog.component.html',
  styleUrls: ['./profile-dialog.component.css'],
  providers: [ProfileService],
})
export class ProfileDialogComponent implements OnInit {
  public profiles: Profile;
  public postProfile: Profile;
  public asc: boolean = true;
  public page: number = 0;
  constructor(
    private profileService: ProfileService,
    public dialog: MatDialog
  ) {
    this.postProfile = new Profile(0,'', 1);
  }

  ngOnInit(): void {}

  getProfiles() {
    this.profileService.getProfiles(this.page, 6, 'name', this.asc).subscribe({
      next: (response: any) => {
        this.profiles = response.content;
      },
    });
  }

  closeDialog() {
    this.getProfiles();
    this.dialog.closeAll();
  }

  newProfile(addForm) {
    this.profileService.postProfile(this.postProfile).subscribe({
      next: () => {
        addForm.reset();
        Swal.fire({
          icon: 'success',
          text: 'Perfil agregado exitosamente.',
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
