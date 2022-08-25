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

  constructor(private profileService: ProfileService) {
    this.postProfile = new Profile('', 1);
  }

  ngOnInit(): void {
    this.getProfiles();
  }

  getProfiles() {
    this.profileService.getProfiles(0, 10, 'name', true).subscribe({
      next: (response: any) => {
        this.profiles = response.content;
      },
    });
  }

  newProfile(addForm) {
    this.profileService.postProfile(this.postProfile).subscribe({
      next: (response: any) => {
        addForm.reset();
        Swal.fire({
          icon: 'success',
          text: 'Perfil agregado exitosamente.',
        });
        this.getProfiles();
      },
      error: (error: any) => {
        Swal.fire({
          icon: 'error',
          text: error.error.errors[0].defaultMessage,
        });
      },
    });
  }
}
