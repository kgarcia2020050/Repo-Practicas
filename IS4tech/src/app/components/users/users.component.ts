import { Component, OnInit } from '@angular/core';
import {
  CdkDragDrop,
  moveItemInArray,
  transferArrayItem,
} from '@angular/cdk/drag-drop';
import { User } from 'src/app/models/user';
import { UserService } from 'src/app/services/user.service';
import { UserDialogComponent } from '../user-dialog/user-dialog.component';
import { MatDialog } from '@angular/material/dialog';
import Swal from 'sweetalert2';
import { Profile } from 'src/app/models/profile';


@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css'],
  providers: [UserService],
})
export class UsersComponent implements OnInit {
  public users: User;
  public listNumbers1;
  public listNumbers2;
  public getUser: User;
  public profiles: Profile;
  public asc: boolean = true;
  public isFirst: boolean;
  public isLast: boolean;
  public page: number = 0;
  public search: any;





  constructor(private userService: UserService, public dialog: MatDialog) {
    this.getUser = new User(0, '', '', 1, 0);
  }

  ngOnInit(): void {
    this.listNumbers1 = [];
    this.listNumbers2 = [];

    for (let index = 0; index < 5; index++) {
      this.listNumbers1.push(index);
    }

    for (let index = 5; index < 10; index++) {
      this.listNumbers2.push(index);
    }
    this.getUsers();
  }

  openDialog() {
    this.dialog.open(UserDialogComponent);
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

  findById(id) {
    this.userService.getUser(id).subscribe({
      next: (response: any) => {
        this.getUser = response;
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
