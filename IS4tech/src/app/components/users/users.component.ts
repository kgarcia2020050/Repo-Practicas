import { Component, OnInit } from '@angular/core';
import {
  CdkDragDrop,
  moveItemInArray,
  transferArrayItem,
} from '@angular/cdk/drag-drop';
import { User } from 'src/app/models/user';
import { UserService } from 'src/app/services/user.service';
import Swal from 'sweetalert2';
import { Router } from '@angular/router';
import { Profile } from 'src/app/models/profile';
import { ProfileService } from 'src/app/services/profile.service';
import { EnterpriseService } from 'src/app/services/enterprise.service';
import { Enterprise } from 'src/app/models/enterprise';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css'],
  providers: [UserService, ProfileService, EnterpriseService],
})
export class UsersComponent implements OnInit {
  public users: User;
  public listNumbers1 = [];
  public listNumbers2 = [];
  public getUser: any;
  public profiles: Profile;
  public asc: boolean = true;
  public isFirst: boolean;
  public isLast: boolean;
  public page: number = 0;
  public search: any;
  public myProfile: Profile;
  public editProfile = false;

  public pageProfile = 0;
  public ascProfile = true;
  public isFirstProfile: boolean;
  public isLastProfile: boolean;

  constructor(
    private userService: UserService,
    private profileService: ProfileService,
    private router: Router,
    private enterpriseService: EnterpriseService
  ) {
    this.getUser = new User(0, '', '', 1, 0, []);
    this.myProfile = new Profile(0, '', 0);
  }

  ngOnInit(): void {
    for (let index = 0; index < 4; index++) {
      this.listNumbers1.push(index);
    }

    for (let index = 4; index < 8; index++) {
      this.listNumbers2.push(index);
    }
    this.getUsers();
    this.getEnterprises()
  }

  openDialog() {
    this.router.navigate(['/openUser']);
  }

  getUsers() {
    this.userService.getUsers(this.page, 6, 'name', this.asc).subscribe({
      next: (response: any) => {
        this.users = response.content;
        this.isFirst = response.first;
        this.isLast = response.last;
      },
    });
  }

  addEnterprise() {
    this.router.navigate(['/openEnterprise']);
  }

  drop($event: CdkDragDrop<number[]>) {
    if ($event.previousContainer === $event.container) {
      moveItemInArray(
        $event.container.data,
        $event.previousIndex,
        $event.currentIndex
      );
    } else {
      transferArrayItem(
        $event.previousContainer.data,
        $event.container.data,
        $event.previousIndex,
        $event.currentIndex
      );
    }
  }

  getEnterprises() {
    this.enterpriseService.getEnterprises().subscribe({
      next: (response: any) => {
        this.listNumbers1=response.content;
      },
    });
  }

  cambiarPerfil() {
    this.profileService
      .getProfiles(this.pageProfile, 6, 'name', this.ascProfile)
      .subscribe({
        next: (response: any) => {
          this.profiles = response.content;
          this.editProfile = true;
          this.isFirstProfile = response.first;
          this.isLastProfile = response.last;
        },
      });
  }

  goBackProfiles() {
    if (!this.isFirstProfile) {
      this.pageProfile--;
      this.cambiarPerfil();
    }
  }

  goAheadProfiles() {
    if (!this.isLastProfile) {
      this.pageProfile++;
      this.cambiarPerfil();
    }
  }

  findById(id) {
    this.userService.getUser(id).subscribe({
      next: (response: any) => {
        this.getUser = response;
        this.profileService.getProfile(this.getUser.profile).subscribe({
          next: (res: any) => {
            this.myProfile = res;
            this.editProfile = false;
          },
        });
      },
    });
  }

  putProfile(id) {
    this.userService.putUser(this.getUser, id).subscribe({
      next: () => {
        Swal.fire({
          icon: 'success',
          text: 'Usuario modificado exitosamente.',
        }).then(() => {
          this.getUsers();
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

  goBack() {
    if (!this.isFirst) {
      this.page--;
      this.getUsers();
    }
  }

  goAhead() {
    if (!this.isLast) {
      this.page++;
      this.getUsers();
    }
  }

  filter() {
    if (this.asc) {
      this.asc = false;
    } else {
      this.asc = true;
    }
    this.getUsers();
  }
}
