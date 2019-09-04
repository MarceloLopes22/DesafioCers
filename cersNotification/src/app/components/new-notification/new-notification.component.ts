import { Component, OnInit, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Notificationn } from 'src/app/model/notificationn.model';
import { NotificationService } from 'src/app/services/notification.service';
import { ActivatedRoute, Router } from '@angular/router';
import { ResponseApi } from 'src/app/model/responseApi.model';

@Component({
  selector: 'app-new-notification',
  templateUrl: './new-notification.component.html'
})
export class NewNotificationComponent implements OnInit {

  @ViewChild("form", {static: true}) form: NgForm;

  notification = new Notificationn('','','',null,null);
  menssage: {}
  classCss: {}
  
  constructor(private notificationService: NotificationService,
    private activatedRoute: ActivatedRoute,
    private router: Router) { }

  ngOnInit() {
    let id: string = this.activatedRoute.snapshot.params['id'];
    if(id != undefined){
      this.findById(id);
    }
  }

  save(){
    this.menssage = {};
    this.notificationService.createOrUpdate(this.notification).subscribe((responseApi: ResponseApi) => {
      this.notification = new Notificationn('','','',null,null);
      let notificationRet: Notificationn = responseApi.data;
      this.form.resetForm();
      this.showMessage({
        type: 'success',
        text: `Aviso registrado ${notificationRet.title} com sucesso`
      });
      this.router.navigate(['/list-notification']);
    }, err =>{
      this.showMessage({
        type: 'error',
        text: err["error"]["erros"][0]
      });
    });    
  }

  findById(id: string) {
    this.notificationService.findById(id).subscribe((responseApi: ResponseApi) =>{
      this.notification = responseApi.data;
    }, err =>{
      this.showMessage({
        type: 'error',
        text: err['error']['errors'][0]
      });
    });
  }

  private showMessage(message: {type: string, text: string}) : void {
    this.menssage = message;
    this.buildClasses(message.type);
    setTimeout(()=>{
      this.menssage = undefined;
    }, 8000);
  }

  private buildClasses(type: string) : void {
    this.classCss = {
      'alert' : true
    }
    this.classCss['alert-'+type] = true;
  }

  getFormGroupClass(isInvalid: boolean, isDirty): {} {
    return {
      'form-group': true,
      'has-error': isInvalid && isDirty,
      'has-success': !isInvalid && isDirty
    };
  }
}
