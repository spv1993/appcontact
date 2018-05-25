import { Component, OnInit } from '@angular/core';

import { UserService } from '../../../service/user/user.service';
import { User } from '../../../model/user';

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})
export class UserListComponent implements OnInit {

	users: Array<User>;

  constructor(private userService: UserService) {
	}

  ngOnInit() {
	this.userService.getUsers().subscribe(data => {
			this.users = data;
		});
  }

}
