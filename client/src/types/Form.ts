import { ChangeEventHandler, FormEventHandler } from 'react';

export interface Input {
  type: string;
  label: string;
  name: string;
  testId: string;
  changeHandler?: ChangeEventHandler;
}

export interface Select extends Input {
  options: string[];
}

export interface Button {
  text: string;
}

export interface Form {
  title: string;
  fields: (Select | Input)[];
  buttonText: string;
  submitHandler?: FormEventHandler<HTMLFormElement>;
}
