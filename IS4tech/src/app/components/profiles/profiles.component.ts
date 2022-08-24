import { Component, OnInit } from '@angular/core';
import { ProfileService } from 'src/app/services/profile.service';

@Component({
  selector: 'app-profiles',
  templateUrl: './profiles.component.html',
  styleUrls: ['./profiles.component.css'],
  providers: [ProfileService],
})
export class ProfilesComponent implements OnInit {
  public profiles: any;

  constructor(private profileService: ProfileService) {}

  ngOnInit(): void {
    this.getProfiles()
  }

  getProfiles() {
    this.profileService.getProfiles().subscribe({
      next:(response:any)=>{
        this.profiles = response;
        console.log(this.profiles)
      }
    })
  }
}
