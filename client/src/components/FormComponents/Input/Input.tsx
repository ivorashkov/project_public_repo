import { trimAndCamelCase } from '../../../services';
import type { Input as InputInterface } from '../../../types';

export const Input = ({ label, type, testId, name, changeHandler }: InputInterface) => {
  return (
    <div className="formRow">
      <label htmlFor={name}>{label}</label>

      <input onChange={changeHandler} type={type} id={name} name={name} data-testid={testId} />
    </div>
  );
};
