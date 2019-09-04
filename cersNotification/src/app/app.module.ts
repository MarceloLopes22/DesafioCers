import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppComponent } from './app.component';
import { HeaderComponent } from './components/header/header.component';
import { FooterComponent } from './components/footer/footer.component';
import { MenuComponent } from './components/menu/menu.component';
import { HomeComponent } from './components/home/home.component';
import { NotificationService } from './services/notification.service';
import { FormsModule } from '@angular/forms';
import { ListNotificationComponent } from './components/list-notification/list-notification.component';
import { routes } from './app.routes';
import { NewNotificationComponent } from './components/new-notification/new-notification.component';
import {HttpClientModule} from '@angular/common/http';
import { DetailNotificationComponent } from './components/detail-notification/detail-notification.component' ;

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    MenuComponent,
    HomeComponent,
    ListNotificationComponent,
    NewNotificationComponent,
    DetailNotificationComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    routes,
    HttpClientModule
  ],
  providers: [NotificationService],
  bootstrap: [AppComponent]
})
export class AppModule { }
