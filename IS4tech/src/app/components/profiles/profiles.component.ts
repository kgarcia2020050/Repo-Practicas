import { Component, OnInit } from '@angular/core';
import { Profile } from 'src/app/models/profile';
import { ProfileService } from 'src/app/services/profile.service';
import Swal from 'sweetalert2';
import { Router } from '@angular/router';

@Component({
  selector: 'app-profiles',
  templateUrl: './profiles.component.html',
  styleUrls: ['./profiles.component.css'],
  providers: [ProfileService],
})
export class ProfilesComponent implements OnInit {
  public profiles: Profile;
  public postProfile: Profile;
  public addProfile: boolean = true;
  public getProfile: Profile;
  public totalPages: number;
  public asc: boolean = true;
  public isFirst: boolean;
  public isLast: boolean;
  public page: number = 0;
  public search: any;
  public changeForm: boolean = false;
  public dataServiceProfile: Profile;
  public itemSelected: number;

  constructor(private profileService: ProfileService, private router: Router) {
    this.getProfile = new Profile(0, '', 1, 0);
    this.postProfile = new Profile(0, '', 1, 0);
  }

  ngOnInit(): void {
    this.getProfiles();
  }

  openDialog() {
    this.addProfile = true;
    this.dataServiceProfile = null;
    this.itemSelected = 0;
    this.getProfile = null;
    this.changeForm = false;
  }

  findById(id) {
    this.profileService.getProfile(id).subscribe({
      next: (response: any) => {
        this.addProfile = false;
        this.itemSelected = id;
        this.dataServiceProfile = Object.assign({}, response);
        this.getProfile = Object.assign({}, response);

        this.validChangeForm();
      },
    });
  }

  newProfile(addForm) {
    this.profileService.postProfile(this.postProfile).subscribe({
      next: () => {
        addForm.reset();
        this.getProfiles();
        Swal.fire({
          icon: 'success',
          text: 'Perfil agregado exitosamente.',
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
            text: error.error.message,
          });
        }
      },
    });
  }

  putProfile(id) {
    this.profileService.putProfile(this.getProfile, id).subscribe({
      next: () => {
        this.addProfile = true;
        Swal.fire({
          icon: 'success',
          text: 'Perfil modificado exitosamente.',
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
            text: error.error.message,
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
        this.totalPages = response.totalPages;
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

  equals(source: Profile, target: Profile): boolean {
    source.permission = source.permission ? 1 : 0;
    source.status = source.status ? 1 : 0;
    return (
      source.name === target.name &&
      source.status === target.status &&
      source.permission === target.permission
    );
  }

  validChangeForm() {
    if (!this.addProfile) {
      this.changeForm = this.equals(this.getProfile, this.dataServiceProfile);
    }
  }
}
