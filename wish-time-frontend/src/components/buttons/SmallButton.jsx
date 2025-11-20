export default function SmallButton({text,onClick,isActive = false}) {
    return (
        <button onClick={onClick} className={`${isActive ?`bg-blue-500`:`bg-white`} rounded-2xl h-full w-full text-base dark:text-white `} >
            {text}
        </button>
    )
}