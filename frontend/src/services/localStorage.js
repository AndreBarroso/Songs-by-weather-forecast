export const getItemFromLocalStorage = async (key) => {
  const localLenght = localStorage.length;
  if (localLenght < 1) return;
  return JSON.parse(localStorage.getItem(key));
};

export const setToLocalStorage = (key, element) => localStorage
  .setItem(key, JSON.stringify(element));