export const trimAndCamelCase = (text: string) => {
  return text.toLocaleUpperCase().trim().replaceAll(' ', '');
};
