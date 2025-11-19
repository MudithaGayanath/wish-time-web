function BaseButton({ colorClass, title, onClickFunction }) {
  return (
    <button
      className={`flex items-center w-full justify-center gap-2 
      ${colorClass} 
      text-white 
      text-base 
      font-medium 
      px-4 py-2 
      rounded-md 
      transition-all 
      hover:scale-[1.02] 
      active:scale-[0.98]
      disabled:opacity-50 
      disabled:cursor-not-allowed 
      
      `}
      onClick={onClickFunction}
    >
      {title}
    </button>
  );
}

export default BaseButton;
