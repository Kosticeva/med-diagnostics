import { Ingredient } from "./ingredient";

export class Drug {

    constructor(
        public id: number,
        public name: string,
        public drugType: any,
        public ingredients: Ingredient[]
    ){}
}
