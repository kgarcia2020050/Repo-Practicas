import { Component, OnInit } from '@angular/core';
import {
  CdkDragDrop,
  moveItemInArray,
  transferArrayItem,
} from '@angular/cdk/drag-drop';
import { User } from 'src/app/models/user';
import { UserService } from 'src/app/services/user.service';
import Swal from 'sweetalert2';
import { Profile } from 'src/app/models/profile';
import { ProfileService } from 'src/app/services/profile.service';
import { EnterpriseService } from 'src/app/services/enterprise.service';
import { UserEnterprise } from 'src/app/models/user-enterprise';
import { Enterprise } from 'src/app/models/enterprise';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css'],
  providers: [UserService, ProfileService, EnterpriseService],
})
export class UsersComponent implements OnInit {
  public postUser: User;
  public users: User;
  public listNumbers1: Enterprise[] = [];
  public empresas: Enterprise[] = [];
  public getUser: User;
  public profiles: Profile;
  public asc: boolean = true;
  public miArray = [];
  public totalPages: number;
  public isFirst: boolean;
  public nuevoArray = [];
  public isLast: boolean;
  public page: number = 0;
  public search: any;
  public search2: any;

  public addUser: boolean = true;

  public pageEnterprise = 0;

  public firstEnterprise: boolean;

  public lastEnterprise: boolean;

  public changeForm: boolean = false;
  public dataServiceChange: User;
  public itemSelected: number;

  public enterpriseNew: UserEnterprise[] = [];
  public changeEnterprise: boolean = false;

  constructor(
    private userService: UserService,
    private profileService: ProfileService,
    private enterpriseService: EnterpriseService
  ) {
    this.getUser = new User(0, '', '', 1, 0, [], {
      id: 0,
      status: 0,
      name: '',
    });
    this.postUser = new User(0, '', '', 1, 0, [], {
      id: 0,
      status: 0,
      name: '',
    });
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
        this.totalPages = response.totalPages;
        this.isLast = response.last;
      },
    });
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
        this.listNumbers1 = response;
      },
    });
  }

  cambiarPerfil() {
    this.profileService.getStatusProfile().subscribe({
      next: (response: any) => {
        this.profiles = response;
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

  habilitar() {
    this.addUser = true;
    this.itemSelected = 0;
    this.getEnterprises();
  }

  findById(id) {
    this.addUser = false;
    this.getEnterprises();
    this.empresas = [];
    this.userService.getUser(id).subscribe({
      next: (response: any) => {
        this.getUser = response;
        this.itemSelected = id;

        this.dataServiceChange = Object.assign({}, response);
        this.getUser = Object.assign({}, response);
        this.validChangeForm();
        this.userService.getInfoUser(id).subscribe({
          next: (response: any) => {
            this.getUser.empresas = Object.assign([], response.empresas);
            this.enterpriseNew = Object.assign([], response.empresas);

            if (this.getUser.empresas.length > 0) {
              for (const element of this.getUser.empresas) {
                this.miArray = this.listNumbers1.filter(
                  (obj) => obj.name !== element.enterpriseName
                );
                this.listNumbers1 = this.miArray;
              }
            }

            this.validChangeEnterprise();
          },
        });
      },
    });
  }

  putProfile(id) {
    let userId: UserEnterprise[] = [];
    this.nuevoArray = this.getUser.empresas;
    let arr: any = this.listNumbers1;

    userId = arr.filter((id) => {
      return id.userId != null && id.userId != undefined;
    });

    this.getUser.empresas = [];

    this.empresas = this.nuevoArray.filter((obj) => {
      return obj.status >= 0;
    });

    this.empresas.forEach((empresa) => {
      this.getUser.empresas.push({
        enterpriseId: empresa.id,
        id: 0,
        enterpriseName: empresa.name,
      });
    });

    this.userService.putUser(this.getUser, id).subscribe({
      next: () => {
        Swal.fire({
          icon: 'success',
          text: 'Usuario modificado exitosamente.',
        });
        if (userId.length > 0) {
          this.enterpriseService.deleteUserEnterprise(userId).subscribe();
        }
        this.getUsers();
        this.empresas = [];
        this.getEnterprises();
        this.addUser = true;
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

  postUsers(addForm) {
    if (this.empresas.length > 0) {
      this.empresas.forEach((empresa) => {
        this.postUser.empresas.push({
          enterpriseId: empresa.id,
          id: 0,
          enterpriseName: empresa.name,
        });
      });
    }
    this.userService.postUser(this.postUser).subscribe({
      next: () => {
        addForm.reset();
        this.getUsers();
        this.postUser.empresas = [];
        this.empresas = [];
        Swal.fire({
          text: 'Usuario agregado exitosamente',
          icon: 'success',
        });
      },
      error: (error: any) => {
        this.postUser.empresas = [];
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

  equals(source: User, target: User): boolean {
    source.status = source.status ? 1 : 0;
    return (
      source.id === target.id &&
      source.name === target.name &&
      source.status === target.status &&
      source.email === target.email &&
      source.profile === target.profile
    );
  }

  validChangeForm() {
    if (!this.addUser) {
      this.changeForm = this.equals(this.getUser, this.dataServiceChange);
    }
  }

  equalsEnterprise(backUp: Enterprise[], newEnterprise: Enterprise[]) {
    return JSON.stringify(backUp) === JSON.stringify(newEnterprise);
  }

  validChangeEnterprise() {
    if (!this.addUser) {
      let empresas = [];
      let arr = [];
      this.getUser.empresas.forEach((empresas: UserEnterprise) => {
        arr.push({
          id: empresas.enterpriseId,
          name: empresas.enterpriseName,
        });
      });
      this.enterpriseNew.forEach((value: UserEnterprise) => {
        empresas.push({
          id: value.enterpriseId,
          name: value.enterpriseName,
        });
      });
      this.changeEnterprise = this.equalsEnterprise(arr, empresas);
    }
  }
}
