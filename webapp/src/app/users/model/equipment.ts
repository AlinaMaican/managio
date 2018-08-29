import {MabecCode} from "./equipment-mabecCode.model";

export class Equipment {
  constructor(public id: number,
              public name: string,
              public code: string,
              public mabelCode: MabecCode,
              public protectionType: string,
              public size: string,
              public sex: string
  ) {}
}
