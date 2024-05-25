import { Address } from './address';

export class Student{
    studentId:number;
    studentName:string;
    score:number;
    address:Address;

    constructor(id:number, name:string, score:number, address:Address){
        this.studentId = id;
        this.studentName = name;
        this.score = score;
        this.address = address;
    }
}