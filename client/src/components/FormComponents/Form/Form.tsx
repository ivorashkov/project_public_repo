import { Input } from '../Input';
import { Textarea } from '../Textarea';
import { Button } from '../Button';

import { Form as FormInterface } from '../../../types';

export const Form = ({ title, fields, buttonText, submitHandler }: FormInterface) => {
  return (
    <form action="" onSubmit={submitHandler}>
      <>
        <h2 className="formTitle">{title}</h2>

        {fields.map((item, index) => {
          if (item?.type === 'textarea') {
            return (
              <Textarea
                key={item?.type + index}
                type={item?.type}
                label={item?.label}
                testId={item?.testId}
                name={item?.name}
              />
            );
          } else {
            return (
              <Input
                key={item?.type + index}
                type={item?.type}
                label={item?.label}
                testId={item?.testId}
                name={item?.name}
              />
            );
          }
        })}

        <Button text={buttonText} />
      </>
    </form>
  );
};
