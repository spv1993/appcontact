import { Component, OnInit } from '@angular/core';

import { ContactService } from '../../../service/contact/contact.service';
import { Contact } from '../../../model/contact';

@Component({
  selector: 'app-contact-list',
  templateUrl: './contact-list.component.html',
  styleUrls: ['./contact-list.component.css']
})
export class ContactListComponent implements OnInit {

  contacts: Array<Contact>;
  
  constructor(private contactService: ContactService) { }

  ngOnInit() {
	this.contactService.getContacts().subscribe(data => {
      this.contacts = data;
    });
  }

}
