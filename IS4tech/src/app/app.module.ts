import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatDialogModule } from '@angular/material/dialog';
import { AppComponent } from './app.component'; 
import { AppRoutingModule } from './app-routing.module';
import { UsersComponent } from './components/users/users.component';
import { ProfilesComponent } from './components/profiles/profiles.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatProgressBarModule } from '@angular/material/progress-bar';
import { MatIconModule } from '@angular/material/icon';
import { MatDividerModule } from '@angular/material/divider';
import { MatButtonModule } from '@angular/material/button';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatInputModule } from '@angular/material/input';
import { SidenavComponent } from './components/sidenav/sidenav.component';
import { NgxPaginationModule } from 'ngx-pagination';
import { MatSelectModule } from '@angular/material/select';
import { MatSlideToggleModule } from '@angular/material/slide-toggle';
import { SearchPipe } from './pipes/search.pipe';
import { MatFormFieldModule } from '@angular/material/form-field';
import { DragDropModule } from '@angular/cdk/drag-drop';
import { ProfileDialogComponent } from './components/profile-dialog/profile-dialog.component';
import { UserDialogComponent } from './components/user-dialog/user-dialog.component';
import { OpenProfileComponent } from './components/open-profile/open-profile.component';
import { OpenUserComponent } from './components/open-user/open-user.component';
import { OpenEnterpriseComponent } from './components/open-enterprise/open-enterprise.component';
import { EnterpriseDialogComponent } from './components/enterprise-dialog/enterprise-dialog.component';

@NgModule({
  declarations: [
    AppComponent,
    UsersComponent,
    ProfilesComponent,
    SidenavComponent,
    SearchPipe,
    ProfileDialogComponent,
    UserDialogComponent,
    OpenProfileComponent,
    OpenUserComponent,
    OpenEnterpriseComponent,
    EnterpriseDialogComponent,
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatProgressBarModule,
    MatIconModule,
    MatDividerModule,
    MatButtonModule,
    MatSidenavModule,
    MatToolbarModule,
    MatSidenavModule,
    MatButtonModule,
    MatIconModule,
    MatDividerModule,
    MatInputModule,
    FormsModule,
    NgxPaginationModule,
    MatSelectModule,
    MatSlideToggleModule,
    DragDropModule,
    MatDialogModule,
    MatFormFieldModule,
    ReactiveFormsModule,
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
