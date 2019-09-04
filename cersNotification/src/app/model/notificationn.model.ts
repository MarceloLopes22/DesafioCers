import { Moment } from 'moment';

export class Notificationn {

    constructor(public idnotification:string,
        public title:string,
        public description:string,
        public date_publication: Date,
        public date_visualization: Date){}
}

//public date_publication:Moment,
//public date_visualization:Moment