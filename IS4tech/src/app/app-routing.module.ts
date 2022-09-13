import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { EnterpriseComponent } from './components/enterprise/enterprise.component';
import { OpenEnterpriseComponent } from './components/open-enterprise/open-enterprise.component';
import { ProfilesComponent } from './components/profiles/profiles.component';
import { SidenavComponent } from './components/sidenav/sidenav.component';
import { UsersComponent } from './components/users/users.component';

const routes: Routes = [
  { path: '', redirectTo: 'profiles', pathMatch: 'full' },

  { path: 'users', component: UsersComponent },
  { path: 'profiles', component: ProfilesComponent },
  { path: 'sidenav', component: SidenavComponent },
  { path: 'openEnterprise', component: OpenEnterpriseComponent },
  { path: 'enterprise', component: EnterpriseComponent},
  { path: '**', component: ProfilesComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
