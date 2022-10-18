import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ModalDismissReasons, NgbModal } from '@ng-bootstrap/ng-bootstrap';

export class Student {
  constructor(public id: number) {}
}

@Component({
  selector: 'app-student',
  templateUrl: './student.component.html',
  styleUrls: ['./student.component.scss'],
})
export class StudentComponent implements OnInit {
  students: any;
  closeResult: any;
  deleteId: any;
  updateId: any;
  studentUrl = 'http://localhost:9191/student';
  router: any;
  constructor(private http: HttpClient, private modalService: NgbModal) {}

  ngOnInit(): void {
    let response = this.http.get(`${this.studentUrl}`);
    response.subscribe((data) => (this.students = data));
  }

  open(content: any) {
    this.modalService
      .open(content, { ariaLabelledBy: 'modal-basic-title' })
      .result.then(
        (result) => {
          this.closeResult = `Closed with: ${result}`;
        },
        (reason) => {
          this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
        }
      );
  }

  private getDismissReason(reason: any): string {
    if (reason === ModalDismissReasons.ESC) {
      return 'by pressing ESC';
    } else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
      return 'by clicking on a backdrop';
    } else {
      return `with: ${reason}`;
    }
  }

  onSubmit(f: NgForm) {
    console.log(f.form.value);
    this.http
      .post('http://localhost:9191/student/addstudent', f.value)
      .subscribe((result) => {
        console.log(result);
        this.ngOnInit(); // reload the table
      });
    this.modalService.dismissAll(); // dismiss the modal
  }

  openDelete(targetModal: any, student: Student) {
    this.deleteId = student.id;
    this.modalService.open(targetModal, {
      backdrop: 'static',
      size: 'lg',
    });
    console.log(this);
  }

  onDelete() {
    this.http
      .delete('http://localhost:9191/student/' + this.deleteId)
      .subscribe((result) => {
        this.ngOnInit();
        this.modalService.dismissAll();
      });
  }

  openUpdate(contentupdate: any, student: Student) {
    this.updateId = student.id;
    this.modalService
      .open(contentupdate, { ariaLabelledBy: 'modal-basic-title' })
      .result.then(
        (result) => {
          this.closeResult = `Closed with: ${result}`;
        },
        (reason) => {
          this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
        }
      );
  }

  updateSubmit(f: NgForm) {
    console.log(f.form.value);
    this.http
      .patch('http://localhost:9191/student/' + this.updateId, f.value)
      .subscribe((result) => {
        console.log(result);
        this.ngOnInit(); // reload the table
      });
    this.modalService.dismissAll(); // dismiss the modal
  }
}
