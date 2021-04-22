export class Lesson {
    id?: number;
    subject: string;
    date: object;
    time: string;
    duration: number;
    price: number;
    teacher?: object;
    pupil?: object;
  
    constructor(subject: string, date: object, time: string, duration: number, price: number) {
      this.subject = subject;
      this.date = date;
      this.time = time;
      this.duration = duration;
      this.price = price;
    }
  }
  