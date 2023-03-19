import type { Input as TextareaInterface } from '../../../types';

export const Textarea = ({ type, label, name, testId }: TextareaInterface) => {
  return (
    <div className="formRow">
      <label htmlFor={name + type}>{label}</label>

      <textarea name={name + type} id={name + type} data-testid={testId}></textarea>
    </div>
  );
};
