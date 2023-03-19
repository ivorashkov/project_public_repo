import { articleFields } from '../../staticData';
import { Form } from '../../components';

export const CreateArticle = () => {
  return (
    <section>
      <Form
        title={articleFields.title}
        fields={articleFields.fields}
        buttonText={articleFields.buttonText}
      />
    </section>
  );
};
