import {Injectable} from '@angular/core';
import { RxStomp, RxStompConfig } from '@stomp/rx-stomp';

@Injectable({
	providedIn: 'root'
})
export class WebSocketService extends RxStomp {
	constructor() {
		super();
	}

}
