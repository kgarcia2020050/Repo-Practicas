import { Component, OnInit } from '@angular/core';
import { Profile } from 'src/app/models/profile';
import { ProfileService } from 'src/app/services/profile.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-profiles',
  templateUrl: './profiles.component.html',
  styleUrls: ['./profiles.component.css'],
  providers: [ProfileService],
})
export class ProfilesComponent implements OnInit {
  public profiles: Profile;
  public postProfile: Profile;
  public asc: boolean = true;
  public isFirst: boolean;
  public isLast: boolean;
  public page: number = 0;
  public search;

  constructor(private profileService: ProfileService) {
    this.postProfile = new Profile('', 1);
  }

  ngOnInit(): void {
    this.getProfiles();
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

  newProfile(addForm) {
    this.profileService.postProfile(this.postProfile).subscribe({
      next: () => {
        addForm.reset();
        Swal.fire({
          icon: 'success',
          text: 'Perfil agregado exitosamente.',
        });
        this.getProfiles();
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
