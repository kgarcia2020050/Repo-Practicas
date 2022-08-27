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
    this.userService.getUsers().subscribe({
      next: (response: any) => {
        this.users = response.content;
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


}
