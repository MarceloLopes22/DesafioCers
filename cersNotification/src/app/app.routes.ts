import { ListNotificationComponent } from './components/list-notification/list-notification.component';
import { HomeComponent } from './components/home/home.component';
import { Routes, RouterModule } from '@angular/router';
import { ModuleWithProviders } from '@angular/core';
import { NewNotificationComponent } from './components/new-notification/new-notification.component';
import { DetailNotificationComponent } from './components/detail-notification/detail-notification.component';

export const ROUTES: Routes = [
    
    {path: '', component: HomeComponent},
    {path: 'list-notification', component: ListNotificationComponent},
    {path: 'new-notification', component: NewNotificationComponent},
    {path: 'new-notification/:id', component: NewNotificationComponent},
    {path: 'detail-notification/:id', component: DetailNotificationComponent}
]

export const routes: ModuleWithProviders = RouterModule.forRoot(ROUTES);