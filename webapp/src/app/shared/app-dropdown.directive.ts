import {Directive,  HostBinding, HostListener, } from '@angular/core';


@Directive({
  selector: '[appDropdown]'
})
export class AppDropdownDirective {

  @HostBinding('class.open') classOpen = false;

  @HostListener('click') onClick() {
    this.classOpen = !this.classOpen;
  }

}
