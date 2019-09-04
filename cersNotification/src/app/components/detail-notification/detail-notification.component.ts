import { Component, OnInit } from '@angular/core';
import { Notificationn } from 'src/app/model/notificationn.model';
import { NotificationService } from 'src/app/services/notification.service';
import { ActivatedRoute, Router } from '@angular/router';
import { ResponseApi } from 'src/app/model/responseApi.model';
import * as moment from 'moment';

@Component({
  selector: 'app-detail-notification',
  templateUrl: './detail-notification.component.html'
})
export class DetailNotificationComponent implements OnInit {

  notification = new Notificationn('','','',null,null);
  menssage: {};
  classCss: {};

  constructor(private notificationService: NotificationService,
              private route: ActivatedRoute,
              private router: Router
    ) {
   }

  ngOnInit() {
    let id:string = this.route.snapshot.params['id'];
    if(id != undefined) {
      this.notificationService.findById(id).subscribe((responseApi:ResponseApi) =>{
        this.notification = responseApi.data;

        this.notification.date_visualization = new Date();
        this.notificationService.createOrUpdate(this.notification).subscribe((responseApi:ResponseApi) =>{
            this.notification = responseApi.data;
        }, err =>{
          this.showMessage({
            type: 'error',
            text: err['error']['errors'][0]
          });
        });
      });
    }
  }

  private showMessage(message: {type: string, text: string}) : void {
    this.menssage = message;
    this.buildClasses(message.type);
    setTimeout(()=>{
      this.menssage = undefined;
    }, 3000);
  }

  private buildClasses(type: string) : void {
    this.classCss = {
      'alert' : true
    }
    this.classCss['alert-'+type] = true;
  }

  voltar(){
    this.router.navigate(['/list-notification'])
  }

}
