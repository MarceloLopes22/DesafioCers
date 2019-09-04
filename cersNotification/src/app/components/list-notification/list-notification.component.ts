import { DialogServiceService } from './../../services/dialog-service.service';
import { Component, OnInit } from '@angular/core';
import { NotificationService } from 'src/app/services/notification.service';
import { Router } from '@angular/router';
import { ResponseApi } from 'src/app/model/responseApi.model';

@Component({
  selector: 'app-list-notification',
  templateUrl: './list-notification.component.html'
})
export class ListNotificationComponent implements OnInit {

  page:number = 0;
  count:number = 5;
  pages:Array<number>;
  message: {};
  classCss: {};
  listNotification = [];

  constructor(
    private dialogService: DialogServiceService,
    private notificationService: NotificationService,
    private router: Router
  ) {
   }

  ngOnInit() {
    this.findAll(this.page, this.count);
  }

  findAll(page: number, count:number) {
    this.notificationService.findAll(page, count).subscribe((responseApi: ResponseApi) => {
      this.listNotification = responseApi['data']['content'];
      this.pages = new Array(responseApi['data']['totalPages']);
    }, err => {
      this.showMessage({
        type: 'error',
        text: err['error']['erros'][0]
      });
    });
  }

  edit(id:string) {
    this.router.navigate(['/new-notification', id]);
  }

  delete(id: string) {
    this.dialogService.confirm("Você quer deletar esse aviso ?")
    .then((canDelete:boolean) =>{
      if(canDelete) {
        this.message = {};
        this.notificationService.delete(id).subscribe((responseApi: ResponseApi) =>{
          this.showMessage({
            type: 'success',
            text: 'Registro deletado.'
          });
          this.findAll(this.page, this.count);
        }, err => {
          this.showMessage({
            type: 'error',
            text: err['error']['erros'][0]
          });
        });
      }
    });
  }

  detail(id:string) {
    this.router.navigate(['/detail-notification',id]);
  }

  setNextPage(event:any) {
    event.preventDefault(); // evitar reload na tela
    if(this.page+1 < this.pages.length) {
      this.page = this.page + 1;
      this.findAll(this.page, this.count);
    }
  }

  setPreviousPage(event:any) {
    event.preventDefault(); // evitar reload na tela
    if(this.page+1 < this.pages.length) {
      this.page = this.page - 1;
      this.findAll(this.page, this.count);
    }
  }

  setPage(i:number, event:any) {
    event.preventDefault(); // evitar reload na tela
      this.page = i;
      this.findAll(this.page, this.count);
  }

  private showMessage(message: {type: string, text: string}) : void {
    this.message = message;
    this.buildClasses(message.type);
    setTimeout(()=>{
      this.message = undefined;
    }, 3000);
  }

  private buildClasses(type: string) : void {
    this.classCss = {
      'alert' : true
    }
    this.classCss['alert-'+type] = true;
  }

}
