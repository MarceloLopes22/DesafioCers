import { Notificationn } from './../model/notificationn.model';
import { Injectable } from '@angular/core';
import { NOTIFICATION_API } from './notification.api';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class NotificationService {

  constructor(private http: HttpClient) { }

  createOrUpdate(notificationn: Notificationn) {
    if(notificationn.idnotification != null && notificationn.idnotification != '') {
      return this.http.put(`${NOTIFICATION_API}/api/notification`, notificationn);
    }
    return this.http.post(`${NOTIFICATION_API}/api/notification`, notificationn);
  }

  findAll(page:number, count: number) {
    return this.http.get(`${NOTIFICATION_API}/api/notification/${page}/${count}`);
  }

  findById(id:string) {
    return this.http.get(`${NOTIFICATION_API}/api/notification/${id}`);
  }
  
  delete(id:string) {
    return this.http.delete(`${NOTIFICATION_API}/api/notification/${id}`);
  }

}
