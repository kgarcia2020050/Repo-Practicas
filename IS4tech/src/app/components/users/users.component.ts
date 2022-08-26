import { Component, OnInit } from '@angular/core';
import { CdkDragDrop, moveItemInArray, transferArrayItem } from '@angular/cdk/drag-drop';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css'],
})
export class UsersComponent implements OnInit {
  public users: any;
  public listNumbers1;
  public listNumbers2;

  constructor() {}

  ngOnInit(): void {
    this.listNumbers1 = [];
    this.listNumbers2 = [];

    for (let index = 0; index < 5; index++) {
      this.listNumbers1.push(index);
    }

    for (let index = 5; index < 10; index++) {
      this.listNumbers2.push(index);
    }
  }

  drop($event: CdkDragDrop<number[]>){

    if($event.previousContainer === $event.container){
      moveItemInArray(
        $event.container.data,
        $event.previousIndex,
        $event.currentIndex
      )
    }else{
      transferArrayItem(
        $event.previousContainer.data,
        $event.container.data,
        $event.previousIndex,
        $event.currentIndex
      );
    }


  }


}
