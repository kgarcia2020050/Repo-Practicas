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
import { UserEnterprise } from 'src/app/models/user-enterprise';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css'],
  providers: [UserService, ProfileService, EnterpriseService],
})
export class UsersComponent implements OnInit {
  public postUser: User;
  public users: User;
  public listNumbers1 = [];
  public empresas = [];
  public getUser: User;
  public profiles: Profile;
  public asc: boolean = true;
  public isFirst: boolean;
  public isLast: boolean;
  public p: number = 1;
  public p2: number = 1;
  public page: number = 0;
  public search: any;
  public myProfile: Profile;
  public editProfile = false;

  public addUser: boolean = true;

  public pageEnterprise = 0;

  public editEnterprises: boolean = false;

  public firstEnterprise: boolean;

  public lastEnterprise: boolean;

  public changeForm: boolean = false;
  public dataServiceChange: User;
  public itemSelected: number;

  constructor(
    private userService: UserService,
    private profileService: ProfileService,
    private router: Router,
    private enterpriseService: EnterpriseService
  ) {
    this.getUser = new User(0, '', '', 1, 0, []);
    this.postUser = new User(0, '', '', 1, 0, []);
    this.myProfile = new Profile(0, '', 0);
  }

  ngOnInit(): void {
    this.getUsers();
    this.cambiarPerfil();
    this.getEnterprises();
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

  asignarEmpresas() {
    this.editEnterprises = true;
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
    this.enterpriseService
      .getEnterprises(this.pageEnterprise, 4, 'name', true)
      .subscribe({
        next: (response: any) => {
          this.listNumbers1 = response.content;
          this.firstEnterprise = response.first;
          this.lastEnterprise = response.last;
        },
      });
  }

  cambiarPerfil() {
    this.profileService.getStatusProfile().subscribe({
      next: (response: any) => {
        this.profiles = response;
        this.editProfile = true;
      },
    });
  }

  goBackEnterprise() {
    if (!this.firstEnterprise) {
      this.pageEnterprise--;
      this.getEnterprises();
    }
  }

  goAheadEnterprise() {
    if (!this.lastEnterprise) {
      this.pageEnterprise++;
      this.getEnterprises();
    }
  }

  findById(id) {
    this.empresas = [];
    this.addUser = false;
    this.getEnterprises();
    this.userService.getUser(id).subscribe({
      next: (response: any) => {
        this.editEnterprises = false;
        this.getUser = response;
        this.itemSelected = id;

        this.dataServiceChange = Object.assign({}, response);
        this.getUser = Object.assign({}, response);
        this.validChangeForm();

        this.profileService.getProfile(this.getUser.profile).subscribe({
          next: (res: any) => {
            this.myProfile = res;
            this.editProfile = false;
            this.userService.getInfoUser(id).subscribe({
              next: (response: any) => {
                this.getUser.empresas = response.empresas;
              },
            });
          },
        });
      },
    });
  }

  putProfile(id) {
    if (this.editEnterprises) {
      this.empresas.forEach((empresa) => {
        this.getUser.empresas.push({
          enterpriseId: empresa.id,
          id: 0,
          enterpriseName: empresa.name,
        });
      });
    }

    this.userService.putUser(this.getUser, id).subscribe({
      next: () => {
        Swal.fire({
          icon: 'success',
          text: 'Usuario modificado exitosamente.',
        });
        this.getUsers();
        this.empresas = [];
        this.getEnterprises();
        this.addUser = true;
      },
      error: (error: any) => {
        this.empresas = [];
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
          if (
            error.error.message ==
            'Te has asignado la misma empresa mas de una vez.'
          ) {
            this.getEnterprises();
          }
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

  deleteUserEnterprise(id, name, userId) {
    Swal.fire({
      title: '¿Estás seguro?',
      text: 'Ya no estarás asignado a la empresa ' + name + '.',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Borrar asignación.',
      cancelButtonText: 'Cancelar.',
    }).then((result) => {
      if (result.isConfirmed) {
        this.enterpriseService.deleteUserEnterprise(id).subscribe({
          next: () => {
            Swal.fire({
              icon: 'success',
              text: 'Asignación eliminada exitosamente.',
            });
            this.findById(userId);
          },
          error: (error: any) => {
            Swal.fire({
              icon: 'error',
              text: error.error.message,
            });
          },
        });
      }
    });
  }

  postUsers(addForm) {
    this.empresas.forEach((empresa) => {
      this.postUser.empresas.push({
        enterpriseId: empresa.id,
        id: 0,
        enterpriseName: empresa.name,
      });
    });
    this.userService.postUser(this.postUser).subscribe({
      next: () => {
        addForm.reset();
        this.getUsers();
        this.postUser.empresas = [];
        Swal.fire({
          text: 'Usuario agregado exitosamente',
          icon: 'success',
        });
      },
      error: (error: any) => {
        this.postUser.empresas = [];
        this.getUsers();
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
          if (
            error.error.message ==
            'Te has asignado la misma empresa mas de una vez, edita tu perfil para asignarte las empresas que deseas.'
          ) {
            this.getUsers();
            addForm.reset();
          }
        }
      },
    });
  }

  equals(source: User, target: User): boolean {
    return (
      source.id === target.id &&
      source.name === target.name &&
      source.status === target.status &&
      source.email === target.email &&
      source.profile === target.profile &&
      source.empresas === target.empresas
    );
  }

  validChangeForm() {
    if (!this.addUser) {
      this.changeForm = this.equals(this.getUser, this.dataServiceChange);
    }
  }

  equalsEnterprise(backUp: UserEnterprise, newEnterprise: UserEnterprise) {
    console.log(backUp);
    console.log(newEnterprise);
    return JSON.stringify(backUp) === JSON.stringify(newEnterprise);
  }

  validChangeEnterprise() {
    if (!this.addEnterprise) {
    }
  }
}
