import { Button as ButtonInterface } from "../../../types";

export const Button = ({ text }: ButtonInterface) => {
  return (
    <div className="form__row">
      <button className="btn">{text}</button>
    </div>
  );
};
