import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ProfilesComponent } from './components/profiles/profiles.component';
import { SidenavComponent } from './components/sidenav/sidenav.component';
import { UsersComponent } from './components/users/users.component';

const routes: Routes = [
  { path: '', component: ProfilesComponent },
  {path: 'users', component: UsersComponent},
  {path: 'profiles', component: ProfilesComponent},
  {path: 'sidenav', component: SidenavComponent}


];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
