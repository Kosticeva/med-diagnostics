import { Ingredient } from "./ingredient";

export class Drug {

    constructor(
        public id: id,
        public name: string,
        public drugType: any,
        public ingredients: Ingredient[]
    ){}
}
