import { Component, OnInit } from '@angular/core';
import {Person, TheaterService} from '../theater.service';

@Component({
  selector: 'app-admin-users',
  templateUrl: './admin-users.component.html',
  styleUrls: ['./admin-users.component.css']
})
export class AdminUsersComponent implements OnInit {
  private theaterService: TheaterService;
  public users: Person[];
  public userToEdit: number = null;

  constructor(theaterService: TheaterService) {
    this.theaterService = theaterService;
  }

  ngOnInit(): void {
    this.reloadPersons();
  }

  addNewUser(form: HTMLFormElement): void {
    const name = (form.elements.namedItem('newName') as HTMLInputElement).value;
    const email = (form.elements.namedItem('newEmail') as HTMLInputElement).value;
    const passwd = (form.elements.namedItem('newPassword') as HTMLInputElement).value;

    const newUser = new Person(undefined);
    newUser.name = name;
    newUser.email = email;
    newUser.password = passwd;
    this.theaterService.createPerson(newUser).subscribe(next => {
      (form.elements.namedItem('newName') as HTMLInputElement).value = '';
      (form.elements.namedItem('newEmail') as HTMLInputElement).value = '';
      (form.elements.namedItem('newPassword') as HTMLInputElement).value = '';
      this.reloadPersons();
    }, error => console.log(error));
  }

  reloadPersons(): void {
    this.theaterService.getAllPerson().subscribe(next => this.users = next, error => console.log(error));
  }

  deleteUser(user: Person): void {
    if (!confirm('Biztos törölni akarod a(z) ' + user.name + ' nevű felhasználót??')) { return; }
    this.theaterService.deletePerson(user).subscribe(next => this.reloadPersons(), error => console.log(error));
  }

  modifyUser(user: Person): void {
    this.userToEdit = null;
    if (!confirm('Módósítások megtartása?')) { return; }
    this.theaterService.modifyPerson(user).subscribe(next => this.reloadPersons(), error => console.log(error));
  }
}
