import { Nav } from '../components';

export interface Nav {
  path: string;
  text: string;
}

export interface NavItems {
  links: Nav[];
}
