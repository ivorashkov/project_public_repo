import { Button as ButtonInterface } from '../../../types';

export const Button = ({ text }: ButtonInterface) => {
  return (
    <div className="formRow">
      <button>{text}</button>
    </div>
  );
};
