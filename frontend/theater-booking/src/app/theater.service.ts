import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Urls} from './Urls';

@Injectable({
  providedIn: 'root'
})
export class TheaterService {
  private http: HttpClient;
  private authorizationToken: string;
  public loggedIn = false;

  constructor(http: HttpClient) {
    this.http = http;
  }

  public createPerson(person: Person): Observable<Person> {
    return new Observable<Person>(observer => {
      this.startRequest<Person>(RequestType.PUT, Urls.PERSON_CREATE, person).toPromise().then(p => {
        const parsed = Person.handleInstancing(p);
        observer.next(parsed);
        observer.complete();
      }).catch(reason => {
        observer.error(reason);
        observer.complete();
      });
    });
  }
  public getAllPerson(): Observable<Person[]> {
    return new Observable<Person[]>(observer => {
      this.startRequest<Person[]>(RequestType.GET, Urls.PERSON_GET_ALL).toPromise().then(ps => {
        const parsed = [];
        for (const p of ps) {
          parsed.push(Person.handleInstancing(p));
        }
        observer.next(parsed);
        observer.complete();
      }).catch(reason => {
        observer.error(reason);
        observer.complete();
      });
    });
  }
  public getPersonByEmail(email: string): Observable<Person> {
    return new Observable<Person>(observer => {
      this.startRequest<Person>(RequestType.GET, Urls.PERSON_GET_BY_EMAIL, email).toPromise().then(p => {
        const parsed = Person.handleInstancing(p);
        observer.next(parsed);
        observer.complete();
      }).catch(reason => {
        observer.error(reason);
        observer.complete();
      });
    });
  }
  public getPersonByName(name: string): Observable<Person[]> {
    return new Observable<Person[]>(observer => {
      this.startRequest<Person[]>(RequestType.GET, Urls.PERSON_GET_BY_NAME, name).toPromise().then(ps => {
        const parsed = [];
        for (const p of ps) {
          parsed.push(Person.handleInstancing(p));
        }
        observer.next(parsed);
        observer.complete();
      }).catch(reason => {
        observer.error(reason);
        observer.complete();
      });
    });
  }
  public getCurrentPerson(): Observable<Person> {
    return new Observable<Person>(observer => {
      this.startRequest<Person>(RequestType.GET, Urls.PERSON_GET_CURRENT).toPromise().then(p => {
        const parsed = Person.handleInstancing(p);
        observer.next(parsed);
        observer.complete();
      }).catch(reason => {
        observer.error(reason);
        observer.complete();
      });
    });
  }
  public assignRoleToPerson(person: Person, role: Role): Observable<Person> {
    const body = {first: person, second: role};
    return new Observable<Person>(observer => {
      this.startRequest<Person>(RequestType.PATCH, Urls.PERSON_ASSIGN_ROLE, body).toPromise().then(p => {
        const parsed = Person.handleInstancing(p);
        observer.next(parsed);
        observer.complete();
      }).catch(reason => {
        observer.error(reason);
        observer.complete();
      });
    });
  }
  public removeRoleFromPerson(person: Person, role: Role): Observable<Person> {
    const body = {first: person, second: role};
    return new Observable<Person>(observer => {
      this.startRequest<Person>(RequestType.PATCH, Urls.PERSON_REMOVE_ROLE, body).toPromise().then(p => {
        const parsed = Person.handleInstancing(p);
        observer.next(parsed);
        observer.complete();
      }).catch(reason => {
        observer.error(reason);
        observer.complete();
      });
    });
  }
  public modifyPerson(person: Person): Observable<Person> {
    return new Observable<Person>(observer => {
      this.startRequest<Person>(RequestType.PATCH, Urls.PERSON_MODIFY, person).toPromise().then(p => {
        const parsed = Person.handleInstancing(p);
        observer.next(parsed);
        observer.complete();
      }).catch(reason => {
        observer.error(reason);
        observer.complete();
      });
    });
  }
  public deletePerson(person: Person): Observable<boolean> {
    return this.startRequest<boolean>(RequestType.DELETE, Urls.PERSON_DELETE, person);
  }

  public createPlay(play: Play): Observable<Play> {
    return new Observable<Play>(observer => {
      this.startRequest<Play>(RequestType.PUT, Urls.PLAY_CREATE, play).toPromise().then(p => {
        const parsed = Play.handleInstancing(p);
        observer.next(parsed);
        observer.complete();
      }).catch(reason => {
        observer.error(reason);
        observer.complete();
      });
    });
  }
  public getAllPlay(): Observable<Play[]> {
    return new Observable<Play[]>(observer => {
      this.startRequest<Play[]>(RequestType.GET, Urls.PLAY_GET_ALL).toPromise().then(ps => {
        const parsed = [];
        for (const p of ps) {
          parsed.push(Play.handleInstancing(p));
        }
        observer.next(parsed);
        observer.complete();
      }).catch(reason => {
        observer.error(reason);
        observer.complete();
      });
    });
  }
  public getPlayByName(name: string): Observable<Play[]> {
    return new Observable<Play[]>(observer => {
      this.startRequest<Play[]>(RequestType.GET, Urls.PLAY_GET_BY_NAME, name).toPromise().then(ps => {
        const parsed = [];
        for (const p of ps) {
          parsed.push(Play.handleInstancing(p));
        }
        observer.next(parsed);
        observer.complete();
      }).catch(reason => {
        observer.error(reason);
        observer.complete();
      });
    });
  }
  public modifyPlay(play: Play): Observable<Play> {
    return new Observable<Play>(observer => {
      this.startRequest<Play>(RequestType.PATCH, Urls.PLAY_MODIFY, play).toPromise().then(p => {
        const parsed = Play.handleInstancing(p);
        observer.next(parsed);
        observer.complete();
      }).catch(reason => {
        observer.error(reason);
        observer.complete();
      });
    });
  }
  public deletePlay(play: Play): Observable<boolean> {
    return this.startRequest<boolean>(RequestType.DELETE, Urls.PLAY_DELETE, play);
  }

  public createReservation(reservation: Reservation): Observable<Reservation> {
    return new Observable<Reservation>(observer => {
      this.startRequest<Reservation>(RequestType.PUT, Urls.RESERVATION_CREATE, reservation).toPromise().then(p => {
        const parsed = Reservation.handleInstancing(p);
        observer.next(parsed);
        observer.complete();
      }).catch(reason => {
        observer.error(reason);
        observer.complete();
      });
    });
  }
  public getAllReservation(): Observable<Reservation[]> {
    return new Observable<Reservation[]>(observer => {
      this.startRequest<Reservation[]>(RequestType.GET, Urls.RESERVATION_GET_ALL).toPromise().then(ps => {
        const parsed = [];
        for (const p of ps) {
          parsed.push(Reservation.handleInstancing(p));
        }
        observer.next(parsed);
        observer.complete();
      }).catch(reason => {
        observer.error(reason);
        observer.complete();
      });
    });
  }
  public getReservationByPlay(play: Play): Observable<Reservation[]> {
    return new Observable<Reservation[]>(observer => {
      this.startRequest<Reservation[]>(RequestType.GET, Urls.RESERVATION_GET_BY_PLAY, play).toPromise().then(ps => {
        const parsed = [];
        for (const p of ps) {
          parsed.push(Reservation.handleInstancing(p));
        }
        observer.next(parsed);
        observer.complete();
      }).catch(reason => {
        observer.error(reason);
        observer.complete();
      });
    });
  }
  public getReservationByRoom(room: Room): Observable<Reservation[]> {
    return new Observable<Reservation[]>(observer => {
      this.startRequest<Reservation[]>(RequestType.GET, Urls.RESERVATION_GET_BY_ROOM, room).toPromise().then(ps => {
        const parsed = [];
        for (const p of ps) {
          parsed.push(Reservation.handleInstancing(p));
        }
        observer.next(parsed);
        observer.complete();
      }).catch(reason => {
        observer.error(reason);
        observer.complete();
      });
    });
  }
  public getReservationBySchedule(schedule: Schedule): Observable<Reservation[]> {
    return new Observable<Reservation[]>(observer => {
      this.startRequest<Reservation[]>(RequestType.GET, Urls.RESERVATION_GET_BY_SCHEDULE, schedule).toPromise().then(ps => {
        const parsed = [];
        for (const p of ps) {
          parsed.push(Reservation.handleInstancing(p));
        }
        observer.next(parsed);
        observer.complete();
      }).catch(reason => {
        observer.error(reason);
        observer.complete();
      });
    });
  }
  public getReservationByPerson(person: Person): Observable<Reservation[]> {
    return new Observable<Reservation[]>(observer => {
      this.startRequest<Reservation[]>(RequestType.GET, Urls.RESERVATION_GET_BY_PERSON, person).toPromise().then(ps => {
        const parsed = [];
        for (const p of ps) {
          parsed.push(Reservation.handleInstancing(p));
        }
        observer.next(parsed);
        observer.complete();
      }).catch(reason => {
        observer.error(reason);
        observer.complete();
      });
    });
  }
  public modifyReservation(reservation: Reservation): Observable<Reservation> {
    return new Observable<Reservation>(observer => {
      this.startRequest<Reservation>(RequestType.PATCH, Urls.RESERVATION_MODIFY, reservation).toPromise().then(p => {
        const parsed = Reservation.handleInstancing(p);
        observer.next(parsed);
        observer.complete();
      }).catch(reason => {
        observer.error(reason);
        observer.complete();
      });
    });
  }
  public deleteReservation(reservation: Reservation): Observable<boolean> {
    return this.startRequest<boolean>(RequestType.DELETE, Urls.RESERVATION_DELETE, reservation);
  }

  public createRoom(room: Room): Observable<Room> {
    return new Observable<Room>(observer => {
      this.startRequest<Room>(RequestType.PUT, Urls.ROOM_CREATE, room).toPromise().then(p => {
        const parsed = Room.handleInstancing(p);
        observer.next(parsed);
        observer.complete();
      }).catch(reason => {
        observer.error(reason);
        observer.complete();
      });
    });
  }
  public getAllRoom(): Observable<Room[]> {
    return new Observable<Room[]>(observer => {
      this.startRequest<Room[]>(RequestType.GET, Urls.ROOM_GET_ALL).toPromise().then(ps => {
        const parsed = [];
        for (const p of ps) {
          parsed.push(Room.handleInstancing(p));
        }
        observer.next(parsed);
        observer.complete();
      }).catch(reason => {
        observer.error(reason);
        observer.complete();
      });
    });
  }
  public getRoomByName(name: string): Observable<Room[]> {
    return new Observable<Room[]>(observer => {
      this.startRequest<Room[]>(RequestType.GET, Urls.ROOM_GET_BY_NAME, name).toPromise().then(ps => {
        const parsed = [];
        for (const p of ps) {
          parsed.push(Room.handleInstancing(p));
        }
        observer.next(parsed);
        observer.complete();
      }).catch(reason => {
        observer.error(reason);
        observer.complete();
      });
    });
  }
  public getRoomByCapacityLessThan(limit: number): Observable<Room[]> {
    return new Observable<Room[]>(observer => {
      this.startRequest<Room[]>(RequestType.GET, Urls.ROOM_GET_CAPACITY_LESS_THAN, limit).toPromise().then(ps => {
        const parsed = [];
        for (const p of ps) {
          parsed.push(Room.handleInstancing(p));
        }
        observer.next(parsed);
        observer.complete();
      }).catch(reason => {
        observer.error(reason);
        observer.complete();
      });
    });
  }
  public getRoomByCapacityGreaterThan(limit: number): Observable<Room[]> {
    return new Observable<Room[]>(observer => {
      this.startRequest<Room[]>(RequestType.GET, Urls.ROOM_GET_CAPACITY_GREATER_THAN, limit).toPromise().then(ps => {
        const parsed = [];
        for (const p of ps) {
          parsed.push(Room.handleInstancing(p));
        }
        observer.next(parsed);
        observer.complete();
      }).catch(reason => {
        observer.error(reason);
        observer.complete();
      });
    });
  }
  public getRoomByCapacityBetween(lower: number, upper: number): Observable<Room[]> {
    const body = {first: lower, second: upper};
    return new Observable<Room[]>(observer => {
      this.startRequest<Room[]>(RequestType.GET, Urls.ROOM_GET_CAPACITY_BETWEEN, body).toPromise().then(ps => {
        const parsed = [];
        for (const p of ps) {
          parsed.push(Room.handleInstancing(p));
        }
        observer.next(parsed);
        observer.complete();
      }).catch(reason => {
        observer.error(reason);
        observer.complete();
      });
    });
  }
  public modifyRoom(room: Room): Observable<Room> {
    return new Observable<Room>(observer => {
      this.startRequest<Room>(RequestType.PATCH, Urls.ROOM_MODIFY, room).toPromise().then(p => {
        const parsed = Room.handleInstancing(p);
        observer.next(parsed);
        observer.complete();
      }).catch(reason => {
        observer.error(reason);
        observer.complete();
      });
    });
  }
  public deleteRoom(room: Room): Observable<boolean> {
    return this.startRequest<boolean>(RequestType.DELETE, Urls.ROOM_DELETE, room);
  }

  public createSchedule(schedule: Schedule): Observable<Schedule> {
    return new Observable<Schedule>(observer => {
      this.startRequest<Schedule>(RequestType.PUT, Urls.SCHEDULE_CREATE, schedule).toPromise().then(p => {
        const parsed = Schedule.handleInstancing(p);
        observer.next(parsed);
        observer.complete();
      }).catch(reason => {
        observer.error(reason);
        observer.complete();
      });
    });
  }
  public getAllSchedule(): Observable<Schedule[]> {
    return new Observable<Schedule[]>(observer => {
      this.startRequest<Schedule[]>(RequestType.GET, Urls.SCHEDULE_GET_ALL).toPromise().then(ps => {
        const parsed = [];
        for (const p of ps) {
          parsed.push(Schedule.handleInstancing(p));
        }
        observer.next(parsed);
        observer.complete();
      }).catch(reason => {
        observer.error(reason);
        observer.complete();
      });
    });
  }
  public getScheduleByPlay(play: Play): Observable<Schedule[]> {
    return new Observable<Schedule[]>(observer => {
      this.startRequest<Schedule[]>(RequestType.GET, Urls.SCHEDULE_GET_BY_PLAY, play).toPromise().then(ps => {
        const parsed = [];
        for (const p of ps) {
          parsed.push(Schedule.handleInstancing(p));
        }
        observer.next(parsed);
        observer.complete();
      }).catch(reason => {
        observer.error(reason);
        observer.complete();
      });
    });
  }
  public getScheduleByRoom(room: Room): Observable<Schedule[]> {
    return new Observable<Schedule[]>(observer => {
      this.startRequest<Schedule[]>(RequestType.GET, Urls.SCHEDULE_GET_BY_ROOM, room).toPromise().then(ps => {
        const parsed = [];
        for (const p of ps) {
          parsed.push(Schedule.handleInstancing(p));
        }
        observer.next(parsed);
        observer.complete();
      }).catch(reason => {
        observer.error(reason);
        observer.complete();
      });
    });
  }
  public getScheduleBefore(limit: Date): Observable<Schedule[]> {
    return new Observable<Schedule[]>(observer => {
      this.startRequest<Schedule[]>(RequestType.GET, Urls.SCHEDULE_GET_BEFORE, limit).toPromise().then(ps => {
        const parsed = [];
        for (const p of ps) {
          parsed.push(Schedule.handleInstancing(p));
        }
        observer.next(parsed);
        observer.complete();
      }).catch(reason => {
        observer.error(reason);
        observer.complete();
      });
    });
  }
  public getScheduleAfter(limit: Date): Observable<Schedule[]> {
    return new Observable<Schedule[]>(observer => {
      this.startRequest<Schedule[]>(RequestType.GET, Urls.SCHEDULE_GET_AFTER, limit).toPromise().then(ps => {
        const parsed = [];
        for (const p of ps) {
          parsed.push(Schedule.handleInstancing(p));
        }
        observer.next(parsed);
        observer.complete();
      }).catch(reason => {
        observer.error(reason);
        observer.complete();
      });
    });
  }
  public getScheduleBetween(begin: Date, end: Date): Observable<Schedule[]> {
    const body = {first: begin, second: end};
    return new Observable<Schedule[]>(observer => {
      this.startRequest<Schedule[]>(RequestType.GET, Urls.SCHEDULE_GET_BETWEEN, body).toPromise().then(ps => {
        const parsed = [];
        for (const p of ps) {
          parsed.push(Schedule.handleInstancing(p));
        }
        observer.next(parsed);
        observer.complete();
      }).catch(reason => {
        observer.error(reason);
        observer.complete();
      });
    });
  }
  public modifySchedule(schedule: Schedule): Observable<Schedule> {
    return new Observable<Schedule>(observer => {
      this.startRequest<Schedule>(RequestType.PATCH, Urls.SCHEDULE_MODIFY, schedule).toPromise().then(p => {
        const parsed = Schedule.handleInstancing(p);
        observer.next(parsed);
        observer.complete();
      }).catch(reason => {
        observer.error(reason);
        observer.complete();
      });
    });
  }
  public deleteSchedule(schedule: Schedule): Observable<boolean> {
    return this.startRequest<boolean>(RequestType.DELETE, Urls.SCHEDULE_DELETE, schedule);
  }

  private getRequestHeader(): any {
    if (this.authorizationToken != null) {
      return {Authorization: 'Basic ' + this.authorizationToken, 'Content-Type': 'application/json;charset=UTF-8'};
    } else {
      return {'Content-Type': 'application/json;charset=UTF-8'};
    }
  }
  private startRequest<T>(reqType: RequestType, url: string, body?: any): Observable<T> {
    let reqTypeStr = '';
    switch (reqType) {
      case RequestType.PUT: reqTypeStr = 'PUT'; break;
      case RequestType.GET: reqTypeStr = 'GET'; break;
      case RequestType.POST: reqTypeStr = 'POST'; break;
      case RequestType.PATCH: reqTypeStr = 'PATCH'; break;
      case RequestType.DELETE: reqTypeStr = 'DELETE'; break;
    }
    const result = this.http.request<T>(reqTypeStr, url, {body, headers: this.getRequestHeader()});
    // result.subscribe(_ => {}, error => console.error(error));
    return result;
  }

  public login(email: string, password: string): Observable<Person> {
    this.authorizationToken = btoa(email + ':' + password);
    const result = this.getCurrentPerson();
    this.getCurrentPerson().subscribe(next => this.loggedIn = true);
    return result;
  }

  public register(name: string, email: string, password: string): Observable<Person> {
    const person = new Person(undefined);
    person.name = name;
    person.email = email;
    person.password = password;
    return this.createPerson(person);
  }
}
export class Person {
  constructor(source: any) {
    if (source === undefined) { return; }
    this.copyPrimitiveData(source);
    for (const r of source.reservations) {
      this.reservations.push(Reservation.handleInstancing(source.reservations));
    }
    for (const r of source.roles) {
      this.roles.push(Role.handleInstancing(r));
    }
  }

  public static instances: Map<number, Person> = new Map<number, Person>();
  public id: number;
  public name: string;
  public email: string;
  public password: string;
  public reservations: Reservation[] = [];
  public roles: Role[] = [];
  public static handleInstancing(source: any): Person {
    if (source.id === undefined) { return this.instances.get(source); }
    const p = new Person(undefined);
    this.instances.set(source.id, p);
    const parsed = new Person(source);
    p.copyPrimitiveData(parsed);
    p.copyReferences(parsed);
    return p;
  }

  private copyPrimitiveData(source: any): void {
    this.id = source.id;
    this.name = source.name;
    this.email = source.email;
    this.password = source.password;
  }

  private copyReferences(source: any): void {
    this.reservations = source.reservations;
    this.roles = source.roles;
  }
}
export class Play {
  constructor(source: any) {
    if (source === undefined) { return; }
    this.copyPrimitiveData(source);
    for (const s of source.schedules) {
      this.schedules.push(Schedule.handleInstancing(s));
    }
  }

  public static instances: Map<number, Play> = new Map<number, Play>();
  public id: number;
  public name: string;
  public length: number;
  public logline: string;
  public schedules: Schedule[] = [];
  public static handleInstancing(source: any): Play {
    if (source.id === undefined) { return this.instances.get(source); }
    const p = new Play(undefined);
    this.instances.set(source.id, p);
    const parsed = new Play(source);
    p.copyPrimitiveData(parsed);
    p.copyReferences(parsed);
    return p;
  }

  private copyPrimitiveData(source: any): void {
    this.id = source.id;
    this.name = source.name;
    this.length = source.length;
    this.logline = source.logline;
  }

  private copyReferences(source: any): void {
    this.schedules = source.schedules;
  }
}
export class Reservation {
  constructor(source: any) {
    if (source === undefined) { return; }
    this.id = source.id;
    this.seat = source.seat;
    this.person = Person.handleInstancing(source.person);
    this.schedule = Schedule.handleInstancing(source.schedule);
  }

  public static instances: Map<number, Reservation> = new Map<number, Reservation>();
  public id: number;
  public seat: number;
  public person: Person;
  public schedule: Schedule;
  public static handleInstancing(source: any): Reservation {
    if (source.id === undefined) { return this.instances.get(source); }
    const r = new Reservation(undefined);
    this.instances.set(source.id, r);
    const parsed = new Reservation(source);
    r.copyPrimitiveData(parsed);
    r.copyReferences(parsed);
    return r;
  }

  private copyPrimitiveData(source: any): void {
    this.id = source.id;
    this.seat = source.seat;
  }
  private copyReferences(source: any): void {
    this.person = source.person;
    this.schedule = source.schedule;
  }
}
export class Role {
  constructor(source: any) {
    if (source === undefined) { return; }
    this.copyPrimitiveData(source);
    for (const p of source.people) {
      this.people.push(Person.handleInstancing(p));
    }
  }
  public static instances: Map<number, Role> = new Map<number, Role>();
  public id: number;
  public roleType: RoleType;
  public people: Person[] = [];
  public static handleInstancing(source: any): Role {
    if (source.id === undefined) { return this.instances.get(source); }
    const r = new Role(undefined);
    this.instances.set(source.id, r);
    const parsed = new Role(source);
    r.copyPrimitiveData(parsed);
    r.copyReferences(parsed);
    return r;
  }

  private copyPrimitiveData(source: any): void {
    this.id = source.id;
    this.roleType = source.roleType;
  }

  private copyReferences(source: any): void {
    this.people = source.people;
  }
}
export enum RoleType { USER, ADMIN }
export class Room {
  constructor(source: any) {
    if (source === undefined) { return; }
    this.copyPrimitiveData(source);
    for (const s of source.schedules) {
      this.schedules.push(Schedule.handleInstancing(s));
    }
  }

  public static instances: Map<number, Room> = new Map<number, Room>();
  public id: number;
  public name: string;
  public capacity: number;
  public schedules: Schedule[] = [];
  public static handleInstancing(source: any): Room {
    if (source.id === undefined) { return this.instances.get(source); }
    const r = new Room(undefined);
    this.instances.set(source.id, r);
    const parsed = new Room(source);
    r.copyPrimitiveData(parsed);
    r.copyReferences(parsed);
    return r;
  }

  private copyPrimitiveData(source: any): void {
    this.id = source.id;
    this.name = source.name;
    this.capacity = source.capacity;
  }
  private copyReferences(source: any): void {
    this.schedules = source.schedules;
  }
}
export class Schedule {
  constructor(source: any) {
    if (source === undefined) { return; }
    this.copyPrimitiveData(source);
    for (const r of source.reservations) {
      this.reservations.push(Reservation.handleInstancing(r));
    }
    this.room = Room.handleInstancing(source.room);
    this.play = Play.handleInstancing(source.play);
  }

  public static instances: Map<number, Schedule> = new Map<number, Schedule>();
  public id: number;
  public start: Date;
  public reservations: Reservation[] = [];
  public room: Room;
  public play: Play;
  public static handleInstancing(source: any): Schedule {
    if (source.id === undefined) { return this.instances.get(source); }
    const s = new Schedule(undefined);
    this.instances.set(source.id, s);
    const parsed = new Schedule(source);
    s.copyPrimitiveData(parsed);
    s.copyReferences(parsed);
    return s;
  }

  private copyPrimitiveData(source: any): void {
    this.id = source.id;
    this.start = new Date(source.start);
  }
  private copyReferences(source: any): void {
    this.reservations = source.reservations;
    this.room = source.room;
    this.play = source.play;
  }
}
export enum RequestType {PUT, GET, POST, PATCH, DELETE}
