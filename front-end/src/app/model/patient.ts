import { Allergen } from "./allergen";

export class Patient {

    constructor(
        public firstName: string,
        public lastName: string,
        public allergens: Allergen[],
        public id: number
    ) {}


}
