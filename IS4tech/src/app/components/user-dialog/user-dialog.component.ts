import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/services/user.service';
import { User } from 'src/app/models/user';
import Swal from 'sweetalert2';
import { Profile } from 'src/app/models/profile';
import { ProfileService } from 'src/app/services/profile.service';
import { FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import {
  CdkDragDrop,
  moveItemInArray,
  transferArrayItem,
} from '@angular/cdk/drag-drop';
import { MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-user-dialog',
  templateUrl: './user-dialog.component.html',
  styleUrls: ['./user-dialog.component.css'],
  providers: [UserService, ProfileService],
})
export class UserDialogComponent implements OnInit {
  public postUser: User;
  public listNumbers1;
  public listNumbers2;
  public profiles: Profile;
  public asc: boolean = true;
  public page: number = 0;
  constructor(
    private userService: UserService,
    private profileService: ProfileService,
    public dialogRef: MatDialogRef<UserDialogComponent>,
    private router: Router
  ) {
    this.postUser = new User(0, '', '', 1, 0,[]);
  }

  selectFormControl = new FormControl('', Validators.required);
  ngOnInit(): void {
    this.listNumbers1 = [];
    this.listNumbers2 = [];

    for (let index = 0; index < 4; index++) {
      this.listNumbers1.push(index);
    }

    for (let index = 4; index < 8; index++) {
      this.listNumbers2.push(index);
    }
    this.getProfiles();
  }

  getProfiles() {
    this.profileService.getProfiles(this.page, 6, 'name', this.asc).subscribe({
      next: (response: any) => {
        this.profiles = response.content;
        console.log(this.profiles);
      },
    });
  }

  addEnterprise() {
    this.router.navigate(['/openEnterprise']);
  }

  closeDialog() {
    this.dialogRef.close();
    this.router.navigate(['/users']);
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

  postUsers(addForm) {
    this.userService.postUser(this.postUser).subscribe({
      next: () => {
        addForm.reset();
        Swal.fire({
          text: 'Usuario agregado exitosamente',
          icon: 'success',
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
