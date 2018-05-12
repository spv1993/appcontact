import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { ActivatedRoute, Router } from '@angular/router';
import { NgForm } from '@angular/forms';

import { ContactService } from '../../../service/contact/contact.service';
import { Contact } from '../../../model/contact';

@Component({
  selector: 'app-contact-edit',
  templateUrl: './contact-edit.component.html',
  styleUrls: ['./contact-edit.component.css']
})
export class ContactEditComponent implements OnInit {

  contact: any = {};
  
  sub: Subscription;
  
  constructor(private route: ActivatedRoute,
              private router: Router,
              private contactService: ContactService) {
  }

  ngOnInit() {
    this.sub = this.route.params.subscribe(params => {
      const id = params['id'];
      if (id) {
        this.contactService.get(id).subscribe((contact: any) => {
		  console.log("get contact");
		  console.log(contact);
          if (contact) {
			console.log("init");
			console.log(contact);
            this.contact = contact;
          } else {
            console.log(`Contact with id '${id}' not found, returning to list`);
            this.gotoList();
          }
        });
      }
    });
  }

  ngOnDestroy() {
    this.sub.unsubscribe();
  }

  gotoList() {
    this.router.navigate(['/contacts']);
  }

  save(contact: Contact) {
	console.log("save");
	console.log(contact);
    this.contactService.save(contact).subscribe(result => {
      this.gotoList();
    }, error => console.error(error));
  }

  remove(contactId) {
    console.log("remove id");
	console.log(contactId);
    this.contactService.remove(contactId).subscribe(result => {
      this.gotoList();
    }, error => console.error(error));
  }

}
